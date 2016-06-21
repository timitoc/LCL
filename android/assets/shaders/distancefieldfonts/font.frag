#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D u_texture;
uniform float scale;
uniform float spread;
varying vec4 v_color;
varying vec2 v_texCoord;

void main()
{
    float smoothing = 0.25 / (spread * scale);
    float distance = texture2D(u_texture, v_texCoord).a;
    float alpha = smoothstep(0.5 - smoothing, 0.5 + smoothing, distance) * v_color.a;
    gl_FragColor = vec4(v_color.rgb, alpha);
}