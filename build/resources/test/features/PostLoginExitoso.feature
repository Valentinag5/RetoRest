#language:es
#encoding:UTF-8
#Author: Valentina

Característica: Login exitoso
  yo como usuario de la pagina reqres.in
  quiero hace un login exitoso

  Esquema del escenario: Login Exitoso
    Dado que el usuario esta en la pagina de login exitoso
    Cuando el usuario envia la solicitud de login con el email y la contraseña
    Entonces el usuario visualiza un codigo de respuesta de estado <estado> y un "<token>"
    Ejemplos:
      | estado | token             |
      | 200    | QpwL5tke4Pnpja7X4 |