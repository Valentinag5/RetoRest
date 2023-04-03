#language:es
#encoding:UTF-8
#Author: Valentina

Característica: Enviar peticion get
  yo como usuario de la pagina reqres.in
  quiero enviar una peticion get

  Escenario: Enviar peticion
    Dado que el usuario esta en la pagina de reqres
    Cuando el usuario envia la peticion
    Entonces el usuario visualiza el codigo de respuesta 404

