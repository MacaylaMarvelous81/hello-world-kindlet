# Hello World Kindlet

This is an example Kindlet written for the Kindle 4 NT. It only contains a text
area. It can be used to test a development environment.

## Requirements
If using the `signJar` task, put developer.keystore in the root of the project.
This is not necessary when signing through other means, such as the command
line. The `signJar` task assumes the developer.keystore is the shared developer
keystore.

For compilation, `Kindlet-1.3.jar` needs to be placed in the `libs` directory.
`Kindlet-1.3.jar` is part of the KDK for the Kindle 4 NT. If you have access to
the root filesystem (for instance, via SSH), you can find it in
`/opt/amazon/ebook/lib`. This is not included in the repository.

## Compilation
Use the `jar` gradle task to compile the project into a jar file. The Kindle
will refuse to open the Kindlet unless you sign the resulting jar, which you
can do with the `signJar` task. These tasks will generate warnings because of
the use of legacy Java versions and insecure signing algorithms, but the use
of these old versions are necessary because the Kindle itself uses these old
versions and algorithms.

## Installation
Once you have a signed jar, you can place it in `/mnt/us/documents` on the
Kindle with the `azw2` extension instead of `jar`. In USB Drive Mode, you
can simply place it in the `documents` folder on the Kindle. The Kindlet will
appear on the home page, denoted with the `dev` tag.