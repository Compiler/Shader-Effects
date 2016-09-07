#version 130



varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_tex0;
uniform sampler2D u_tex1;
uniform float alpha;
uniform float timedelta;


void main(){
	 vec2 displacement = texture2D(u_tex1, v_texCoords/42.0f).xy;
	 float ti = v_texCoords.y +displacement.y*0.7-0.15+  (cos(v_texCoords.x * 40.0+timedelta) * 0.003);
	 gl_FragColor = v_color * texture2D(u_tex1, vec2(v_texCoords.x, ti));
	 gl_FragColor.a = alpha;
	
}