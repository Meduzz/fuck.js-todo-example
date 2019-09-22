package se.chimps.fuckjs.example.todo.components

import org.scalajs.dom.html.Input
import org.scalajs.dom.raw.KeyboardEvent
import se.chimps.fuckjs.html.RealTag
import se.chimps.fuckjs.{Component, EventHandler, Mutation, UI}

class TodoInput extends Component with EventHandler {
	override def view():RealTag = div(clazz("panel"))(
		p(clazz("panel-heading"))(text("Todo example")),
		div(clazz("panel-block"))(
			input(clazz("input"), typ("text"), id("todo"), placeholder("Press enter to save"), on("keyup", trigger(e => keyup(e))))()
		)
	)

	override def handle:PartialFunction[Mutation, Unit] = {
		case Typing(text) => {
			if (text.nonEmpty) {
				UI.trigger(DoneTyping(text))
			}
		}
	}

	private def keyup(e:KeyboardEvent):Mutation = {
		if (e.keyCode == 13) {
			val text = e.target.asInstanceOf[Input].value
			e.target.asInstanceOf[Input].value = ""
			Typing(text)
		} else {
			Typing("")
		}
	}
}

case class Typing(text:String) extends Mutation
case class DoneTyping(text:String) extends Mutation