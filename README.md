# fuck.js-todo-example
The obligatory todo example.

To run this example, run ```sbt fastOptJS``` in a terminal and open [index.html](index.html) in a browser.

![The example in action](screenshot.png)

Here the app is only split over 2 components, but there's easily room to make the tabs its own component.
The two components communicate with messages over the ```UI.trigger()``` & ```UI.on()```.
The app is setup in [Main](todo) where the components are mounted into the dom.