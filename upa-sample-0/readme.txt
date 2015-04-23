mvn eclipse:eclipse -Dwtpversion=2.0 

http://localhost:8080/upa-sample-0/hello

http://localhost:8080/upa-sample-0/index.jsp

http://localhost:8080/upa-sample-0/guice/whatever

version 0 se complico, mucha herencia
version 1 no supe que hacer 
version 2: vamos a hacer solamente componentes inyectables. There is no framework. solo un conjunto de utilidades
la idea es que una web app sea 2 modulos: ControllerModules y ServicesModules
Controller tiene un servlet magico que autoinyecta , quizas otro para semi static webs con el application-type correcto
usar http://onami.apache.org/ si hace falta
al principio hago el automapper manual a ver como queda
http://localhost:8080/upa-sample-0/upa/whatever?a=1&b=2&b=2&a.r=lalal
http://localhost:8080/upa-sample-0/upa/Search/findAllTx?param12=lalal&param2=5
getRequestURI:/upa-sample-0/upa/whatever
parameterMap: 
{
  a:[1]
  b:[2, 2]
  a.r:[lalal]
  }

