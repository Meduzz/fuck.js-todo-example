package se.chimps.fuckjs.example.todo.components

import org.scalajs.dom
import org.scalajs.dom.KeyboardEvent
import org.scalajs.dom.html.Input
import se.chimps.fuckjs.{Component, Mutation, UI}

import scalatags.JsDom.all._

class TodoInput extends Component {
	private var defaultText = ""

	override def view() = div(cls := "panel",
		p(cls := "panel-heading", "Todo example"),
		div(cls := "panel-block",
			input(cls:= "input", `type` := "text", id := "todo", placeholder := "Press enter to save", value := defaultText, onkeyup := trigger((e:KeyboardEvent) => Typing(e.keyCode)))
		)
	).render

	override def handle = {
		case Typing(key) => {
			if (key == 13) {
				val todo = dom.document.querySelector("#todo").asInstanceOf[Input].value
				UI.trigger(DoneTyping(todo))
				true
			} else {
				false
			}
		}
	}
}

case class Typing(key:Int) extends Mutation
case class DoneTyping(text:String) extends Mutation