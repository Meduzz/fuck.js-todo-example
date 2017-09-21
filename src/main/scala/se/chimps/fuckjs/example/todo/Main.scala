package se.chimps.fuckjs.example.todo

import se.chimps.fuckjs.{Trigger, UI}
import se.chimps.fuckjs.example.todo.components.{Filter, TodoInput, TodoList}

object Main {
	def main(args:Array[String]): Unit = {
		val input = new TodoInput()
		val list = new TodoList()

		UI.mount(input, "#inputField")
		UI.mount(list, "#todoList")

		UI.route("#todoList")
	  	.on("/all") { (old, data) =>
			  Trigger(Filter("all"))
		  }.on("/done") { (old, data) =>
				Trigger(Filter("done"))
			}.on("/notdone") { (old, data) =>
				Trigger(Filter("not done"))
			}
	}
}
