package se.chimps.fuckjs.example.todo.components

import se.chimps.fuckjs.html.RealTag
import se.chimps.fuckjs.{Component, EventHandler, Mutation}

class TodoList extends Component with EventHandler {
	private var todos:Seq[Todo] = Seq()
	private var filter:String = "all"

	override def view():RealTag = div(clazz("panel"))(
		div(clazz("panel-block"))(
			div(clazz("menu"))(
				tag("ul.menu-list", Seq(),
					for (t <- doFilter(todos)) yield li()(
						input(typ("checkbox"), clazz("checkbox"), if (t.done) {attribute("checked", "checked")} else { title("unchecked") }, on("click", trigger(ToggleTodo(t))))(),
						span()(text(t.text))
					)
				)
			)
		),
		div(clazz("panel-tabs"))(
			a(if (filter == "all") {clazz("is-active")} else { clazz("") }, href("#/all"))(
				span()(text("all")),
				span(clazz("tag is-dark"))(text(s"${todos.size}"))
			),
			a(if (filter == "done") {clazz("is-active")} else { clazz("") }, href("#/done"))(
				span()(text("completed")),
				span(clazz("tag is-success"))(text(s"${todos.count(_.done)}"))
			),
			a(if (filter == "not done") {clazz("is-active")} else { clazz("") }, href("#/notdone"))(
				span()(text("not completed")),
				span(clazz("tag is-danger"))(text(s"${todos.count(!_.done)}"))
			)
		)
	)

	override def handle:PartialFunction[Mutation, Unit] = {
		case ToggleTodo(todo) => {
			val toggled = todo.copy(done = !todo.done)
			todos = todos.filterNot(t => t.text == todo.text) ++ Seq(toggled)
			update()
		}
		case Filter(text) => {
			filter = text
			update()
		}
		case DoneTyping(text) => {
			todos = todos ++ Seq(Todo(false, text))
			update()
		}
	}

	private def doFilter(t:Seq[Todo]):Seq[Todo] = {
		filter match {
			case "all" => t
			case "done" => t.filter(_.done)
			case "not done" => t.filter(!_.done)
		}
	}
}

case class Todo(done:Boolean, text:String)
case class ToggleTodo(todo:Todo) extends Mutation
case class Filter(text:String) extends Mutation