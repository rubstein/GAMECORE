Se hará uso de la terminal de visual para el uso de comandos git. Importante que la ruta sea la del proyecto. No se debe trabajar en la rama main

Comandos útiles para trabajar:

git status -> Muestra el estado de git. Devolverá la rama que se usa y los archivos que han sido editados desde el último commit
git branch -> Muestra las ramas activas usadas en el proyecto. Se mostrará en verde la que se esté usando
git branch <nombre_rama> -> Creación de rama. Es importante estar en la rama main si se usara este comando, ya que las ramas 'salen' de ahí. 
git checkout <nombre_rama> -> Cambio de rama. Hay que hacer commit antes, ya que si no se pierden los cambios realizados.
git pull -> Trae los cambios actualizados desde una rama. Normalmente se hace unicamente git pull en la rama main
git push -> Subida de rama a git. Hay que hacerlo después del commit de la rama que se esté usando
git merge -> Mezclar ramas. Útil si queremos traer los cambios a otra rama


Para un correcto trabajo, seguir estos pasos:

1 -> git status: comprobamos el estado de nuestra rama
      ·si sale main -> git branch <nombre_rama>, para crear la nuestra
      ·si sale nuestra rama -> todo ok

2 -> Editamos el fichero con los comandos que queramos. Una vez hemos comprobado que los comandos funcionan, hacemos commit con un mensaje corto con palabras clave con lo que hemos hecho para no perder los cambios
3 -> Tras hacer commit, vamos a la rama main y hacemos git pull. Esto comprobará si han habido actualizaciones a la rama principal.
      · si no han habido cambios: saldrá actualizado
      · si han habido cambios: se importarán los cambios
4 -> Volvemos a nuestra rama, con git checkout <nuestra_rama> y hacemos git push. Si nos sale un error, copiamos el comando que git sugiere.
5 -> Hay dos opciones:
      · Ir a github: Nos saldrá un aviso de merge. Seguimos los pasos.
      · Con comandos: Iremos a la rama main y escribimos el comando git merge <tu_rama>.



