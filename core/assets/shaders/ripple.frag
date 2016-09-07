#version 130

varying vec2 v_texCoords;
varying vec4 v_color;
 
uniform sampler2D  u_texture;
 
void main (){
 
	gl_FragColor = v_color * texture2D(u_texture, v_texCoords);
 
}