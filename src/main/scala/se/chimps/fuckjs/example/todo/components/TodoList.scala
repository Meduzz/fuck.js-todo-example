package se.chimps.fuckjs.example.todo.components

import se.chimps.fuckjs.{Component, Mutation, UI}

import scalatags.JsDom.all._

class TodoList extends Component {
	private var todos:Seq[Todo] = Seq()
	private var filter:String = "all"

	override def view() = div(cls := "panel",
		div(cls := "panel-block",
			div(cls := "menu",
				ul(cls := "menu-list",
					for (t <- doFilter(todos)) yield li(
						input(`type` := "checkbox", cls := "checkbox", if (t.done) {checked:="checked"}, onclick := trigger(ToggleTodo(t))),
						t.text
					)
				)
			)
		),
		div(cls := "panel-tabs",
			a(if (filter == "all") {cls := "is-active"}, href := "#/all",
				span("all"),
				span(cls := "tag is-dark", todos.size)
			),
			a(if (filter == "done") {cls := "is-active"}, href := "#/done",
				span("completed"),
				span(cls := "tag is-success", todos.count(_.done))
			),
			a(if (filter == "not done") {cls := "is-active"}, href := "#/notdone",
				span("not completed"),
				span(cls := "tag is-danger", todos.count(!_.done))
			)
		)
	).render

	override def handle = {
		case ToggleTodo(todo) => {
			val toggled = todo.copy(done = !todo.done)
			todos = todos.filterNot(t => t.text == todo.text) ++ Seq(toggled)
			true
		}
		case Filter(text) => {
			filter = text
			true
		}
	}

	private def doFilter(t:Seq[Todo]):Seq[Todo] = {
		filter match {
			case "all" => t
			case "done" => t.filter(_.done)
			case "not done" => t.filter(!_.done)
		}
	}

	UI.on({
		case DoneTyping(text) => {
			todos = todos ++ Seq(Todo(false, text))
			update()
		}
	})
}

case class Todo(done:Boolean, text:String)
case class ToggleTodo(todo:Todo) extends Mutation
case class Filter(text:String) extends Mutation