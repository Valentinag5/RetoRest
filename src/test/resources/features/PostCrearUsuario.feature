#language:es
#encoding:UTF-8
#Author: Valentina

Característica: Crear usuario
  yo como usuario de la pagina reqres.in
  quiero crear un usuario

  Esquema del escenario: Crear un usuario
    Dado que el usuario esta en la pagina de creacion
    Cuando el usuario envia la solicitud de registro con el nombre y el trabajo
    Entonces el usuario visualiza un codigo de respuesta de estado <codigo> y un "<nombre>", un "<trabajo>" y un <id>
    Ejemplos:
      | codigo | nombre   | trabajo | id  |
      | 201    | morpheus | leader  | 994 |

