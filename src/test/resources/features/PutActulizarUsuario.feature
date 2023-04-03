#language:es
#encoding:UTF-8
#Author: Valentina

Característica: Actualizar usuario
  yo como usuario de la pagina reqres.in
  quiero actualizar un usuario

  Esquema del escenario: Actualizar usuario
    Dado que el usuario esta en la pagina de actualizacion
    Cuando el usuario envia la solicitud de actualizacion con el nombre y el trabajo
    Entonces el usuario visualiza un codigo de respuesta de estado <estado>, el "<nombre>" y el "<trabajo>"
    Ejemplos:
      | estado | nombre   | trabajo       |
      | 200    | morpheus | zion resident |