#version 130

varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 pos;

uniform sampler2D u_texture;
uniform mat4 u_projTrans;

void main() {
        vec3 color = texture2D(u_texture, v_texCoords).rgb;
        float gray = (color.r + color.g + color.b) / 3.0;
        vec3 grayscale = vec3(gray);
	    gl_FragColor = texture2D(u_texture, v_texCoords);
	   // gl_FragColor.a = pos.x / 255;
}