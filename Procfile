#web: gradle jettyRun -Djetty.httpPort=$PORT
web: java $JAVA_OPTS -DTG_ENV=prod -jar jetty-runner.jar --port $PORT vaadin-builder-demo/build/libs/*.war
