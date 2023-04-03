#language:es
#encoding:UTF-8
#Author: Valentina

Característica: Actualizar usuario patch
  yo como usuario de la pagina reqres.in
  quiero actualizar un usuario

  Esquema del escenario: Actualizar usuario patch
    Dado que el usuario esta en la pagina de actualizacion patch
    Cuando el usuario envia la solicitud de actualizacion patch con el nombre y el trabajo
    Entonces el usuario visualiza un codigo de respuesta de estado <estado>, el nombre "<nombre>" y el trabajo "<trabajo>"
    Ejemplos:
      | estado | nombre | trabajo           |
      | 200    | morpheus | zion resident |