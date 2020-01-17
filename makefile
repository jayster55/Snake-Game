default:
	javac --module-path ${JAVA_FX_HOME}/lib --add-modules javafx.controls *.java

run: default
	java --module-path ${JAVA_FX_HOME}/lib --add-modules javafx.controls Main

clean:
	rm -rf **/*.class
