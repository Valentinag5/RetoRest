#language:es
#encoding:UTF-8
#Author: Valentina

Característica: Registro fallido
  yo como usuario de la pagina reqres.in
  quiero hace un registro fallido

  Esquema del escenario: Registro fallido
    Dado que el usuario esta en la pagina de registro fallido
    Cuando el usuario envia la solicitud de registro con el email
    Entonces el usuario visualiza un codigo de respuesta de estado <estado>
    Ejemplos:
      | estado |
      | 400    |
