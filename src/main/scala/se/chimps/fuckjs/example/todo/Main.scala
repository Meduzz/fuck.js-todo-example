package se.chimps.fuckjs.example.todo

import se.chimps.fuckjs.UI
import se.chimps.fuckjs.example.todo.components.{TodoInput, TodoList}

object Main {
	def main(args:Array[String]): Unit = {
		val input = new TodoInput()
		val list = new TodoList()

		UI.mount(input, "#inputField")
		UI.mount(list, "#todoList")
	}
}
