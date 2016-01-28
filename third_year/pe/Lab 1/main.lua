-- displays a background and an image

-------------- newImage----------------
--Function that displays an image on the screen from a file
-- Library	display.*
-- Return value	DisplayObject
--Syntax (short):
--- display.newImage( filename, x, y])
----filename: name of image on disk, support jpg and png format
----contentCenterX: the center of the content area along the x axis. 

local image = display.newImage( "image.jpg", display.contentCenterX, 
display.contentCenterY )
local background = display.newImage( "background.jpg", display.contentCenterX, display.contentCenterY )


--exercise: Swap the last two lines. What do you notice?

