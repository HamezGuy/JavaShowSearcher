default: runProgram

runProgram: FrontendDeveloperTests.class

IShowSearcherFrontendClass.class: IShowSearcherFrontendClass.java
	javac IShowSearcherFrontendClass.java

FrontendDeveloperTests.class: IShowSearcherFrontEndClass.class FrontendDeveloperTests.java
	javac FrontendDeveloperTests.java

clean:
	rm *.class
