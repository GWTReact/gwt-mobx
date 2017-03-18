# gwt-mobx
GWT Java bindings for MobX and MobX related projects

##Introduction

gwt-mobx provides Java [GWT](http://www.gwtproject.org/) bindings based on the
new JsInterop capabilities introduced in GWT 2.8 for the following MobX related projects:

* [mobx 2.5.2](https://github.com/mobxjs/mobx)
* [mobx-react 3.5.6](https://github.com/mobxjs/mobx-react)
* [mobx-react-devtools 4.2.6](https://github.com/mobxjs/mobx-react-devtools)

This project depends on [gwt-react](https://github.com/GWTReact/gwt-react)

***The API is only partially complete at this point and is highly likely to change.***

##Getting Started

Please take a look at the mobx examples under the [gwt-react-examples](https://github.com/GWTReact/gwt-react-examples) project for
details on how to use the library.

You can download the latest release .jar from Maven Central using the following coordinates:

* **groupId**&nbsp;&nbsp;&nbsp; com.github.gwtreact
* **artifactId**&nbsp;&nbsp;gwt-mobx
* **version**&nbsp;&nbsp;&nbsp;  0.5.0

##Roadmap

* Finish the api
* Provide API's for the popular add-ons
* ~~Support developer tools~~ DONE

##Change log

| Date | Version | Description |
| :---      | :---  | :---  |
| 3/18/2017 | 0.5.0 | Refactored to use more future proof ES6 styled stateful components and added Preact support  |
| 10/28/2016 | 0.4.0 | Support mobx 2.5.2 and GWT 2.8 final release  |
| 6/28/2016 | 0.3.0 | Support mobx 2.3.2  |
| 6/18/2016 | 0.2.0 | Update to use gwt-interop-utils library   |
| 5/18/2016 | 0.1.0 | Initial preview release to Maven Central   |


##Resources

* [Official Mobx documentation](https://mobxjs.github.io/mobx/)
* [State Management Is Easy (slides)](https://speakerdeck.com/mweststrate/state-management-is-easy-introduction-to-mobx)

