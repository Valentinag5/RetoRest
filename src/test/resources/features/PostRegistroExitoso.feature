#language:es
#encoding:UTF-8
#Author: Valentina

Característica: Registro exitoso
  yo como usuario de la pagina reqres.in
  quiero hace un registro exitoso

  Esquema del escenario: Registro Exitoso
    Dado que el usuario esta en la pagina de registro
    Cuando el usuario envia la solicitud de registro con el email y la contraseña
    Entonces el usuario visualiza un codigo de respuesta de estado <estado> y un <id> un "<token>"
    Ejemplos:
      | estado | id | token             |
      | 200    | 4  | QpwL5tke4Pnpja7X4 |
