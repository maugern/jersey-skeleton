Jersey Skeleton [![][travis img]][travis] [![][code-climate img]][code-climate]
===============


To launch local webserver
-------------------------
Clone the repository into your workspace
```sh
git clone https://github.com/maugern/jersey-skeleton.git
```

Download dependancy and run unit test
```sh
mvn verify
```

Launch application in localhost:8080
```sh
mvn jetty:run
```

Or you can pull and run it via Docker :
```sh
docker pull maugern/jersey-skeleton
docker run jersey-skeleton
```

About this
----------
This project fork the original [jersey-skeleton].
I offer no warranty on anything, ever. If you add a new feature or fix a bug,
 it would be nice if you contributed it back to the project.
Don't worry, it's under [![][license img]][license].



[jersey]:https://jersey.java.net
[jersey-skeleton]:https://github.com/tclavier/jersey-skeleton

[travis]:https://travis-ci.org/maugern/jersey-skeleton
[travis img]:https://travis-ci.org/maugern/jersey-skeleton.svg?branch=master

[code-climate]:https://codeclimate.com/github/maugern/jersey-skeleton
[code-climate img]:https://codeclimate.com/github/maugern/jersey-skeleton/badges/gpa.svg

[license]:https://github.com/maugern/jersey-skeleton/blob/master/LICENSE
[license img]:https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000
