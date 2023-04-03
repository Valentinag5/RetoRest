#language:es
#encoding:UTF-8
#Author: Valentina

Característica: Eliminar usuario
  yo como usuario de la pagina reqres.in
  quiero eliminar un usuario

  Esquema del escenario: Eliminar usuario
    Dado que el usuario esta en la pagina de eliminacion
    Cuando el usuario envia la solicitud de eliminacion
    Entonces el usuario visualiza un codigo de respuesta de estado <204>