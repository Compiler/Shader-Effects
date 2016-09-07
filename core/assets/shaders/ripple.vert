#version 130

attribute vec4 a_position;
attribute vec2 a_texCoord0;

varying vec4 v_color;
varying vec2 v_texCoords;
varying vec4 v_position;

uniform mat4 u_projTrans;

void main () {
 
	v_color = vec4 (1, 1, 1, 1);
	v_position = a_position;
	v_texCoords = a_texCoord0;
	gl_Position = u_projTrans * a_position;
	
 }