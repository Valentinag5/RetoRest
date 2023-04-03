#language:es
#encoding:UTF-8
#Author: Valentina

Característica: Listar usuarios en reqres
  yo como usuario de la pagina reqres.in
  quiero listar usuarios

  Escenario: Listar usuarios
    Dado que el usuario esta en la pagina principal
    Cuando el usuario envia la solicitud
    Entonces el usuario visualiza el codigo 200
