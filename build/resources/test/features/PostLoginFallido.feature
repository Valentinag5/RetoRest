#language:es
#encoding:UTF-8
#Author: Valentina

Característica: Login fallido
  yo como usuario de la pagina reqres.in
  quiero hace un login fallido

  Escenario:  Login Fallido
    Dado que el usuario esta en la pagina de login fallido
    Cuando el usuario envia la solicitud de login con el email
    Entonces el usuario visualiza un codigo de respuesta  404