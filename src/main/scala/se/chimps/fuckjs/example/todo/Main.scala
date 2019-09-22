package se.chimps.fuckjs.example.todo

import se.chimps.fuckjs.UI
import se.chimps.fuckjs.example.todo.components.{Filter, TodoInput, TodoList}

object Main {
	def main(args:Array[String]): Unit = {
		val input = new TodoInput()
		val list = new TodoList()

		UI.register(input, "#inputField")
		UI.register(list, "#todoList")

		UI.routing(e => {
			if (e.newURL.endsWith("#/all")) {
				Filter("all")
			} else if (e.newURL.endsWith("#/done")) {
				Filter("done")
			} else if (e.newURL.endsWith("#/notdone")) {
				Filter("not done")
			} else {
				Filter("all")
			}
		})
	}
}
