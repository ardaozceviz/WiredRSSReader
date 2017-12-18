# Wired RSS Reader
This is sample RSS reader app for Android developed in Kotlin which I believe it might be a useful source for those who want to see how Kotlin language works in Android development. It also demonstrates how [xtoj](https://github.com/atakanozceviz/xtoj) api works as well as using coroutines with suspend functions in Kotlin.

## Screenshots

<img src="https://github.com/ardaozceviz/WiredRSSReader/blob/master/screenshots/ss1.png" width="430"/>         <img src="https://github.com/ardaozceviz/WiredRSSReader/blob/master/screenshots/ss2.png" width="430"/>

## Overview
This app basically retrieves the data from [wired](https://www.wired.com/) RSS channel in XML format. And converts it to the JSON using [xtoj](https://github.com/atakanozceviz/xtoj) and represents to the user in RecyclerView. Each item in the list contains title, image and keywords. When you click the one of the items on the list you will be directed to another Activity where you will find more detailed information about the article as well as five most repetitive words and their Turkish translations. I used [Yandex API](https://tech.yandex.com/translate/) for translation. You can obtain free API key from the website provided.

## Libraries used in this project
* [Android Design Support Library](https://developer.android.com/topic/libraries/support-library/packages.html#custom-tabs)
* [Loopj Async Http](https://github.com/loopj/android-async-http)
* [GSON](https://github.com/google/gson)
* [FRESCO](http://frescolib.org/)
* [Anko](https://github.com/Kotlin/anko)
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)

## Build the project
 * Clone or download the project
 * Obtain your API key from [Yandex](https://tech.yandex.com/translate/)
 * Add the following attribute to your "gradle.properties" file using this format.
```groovy
API_KEY = "your_api_key_goes_here"
```
* Build the project!

## Others
* Welcome anyone to raise a new issue.
* Welcome anyone to push a pull request to improve the project.

## Find it helpful?
* Support me by clicking the :star: button on the upper right of this page.
* Spread it to others to let more people to have an access to this project. 

## License
```license
MIT License

Copyright (c) 2017 Arda Özceviz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
