import React from "react";
import { Dimensions } from 'react-native'
import { SvgXml } from "react-native-svg";  

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

export default function MicroInvestingSvg2(){  
  const svgMarkup = `<svg xmlns="http://www.w3.org/2000/svg" width="15.158" height="14.98" viewBox="0 0 15.158 14.98">
  <g id="coin" transform="translate(0 -3)">
    <g id="Grupo_165" data-name="Grupo 165" transform="translate(8.88 10.496)">
      <g id="Grupo_164" data-name="Grupo 164">
        <path id="Caminho_121" data-name="Caminho 121" d="M301.2,258.337a.57.57,0,1,1,.651-.564.3.3,0,1,0,.592,0,1.178,1.178,0,0,0-.947-1.122V256.5a.3.3,0,1,0-.592,0v.154a1.178,1.178,0,0,0-.947,1.122,1.2,1.2,0,0,0,1.243,1.156.57.57,0,1,1-.651.564.3.3,0,0,0-.592,0,1.178,1.178,0,0,0,.947,1.122v.128a.3.3,0,0,0,.592,0v-.128a1.178,1.178,0,0,0,.947-1.122A1.2,1.2,0,0,0,301.2,258.337Z" transform="translate(-299.954 -256.201)" fill="#434343"/>
      </g>
    </g>
    <g id="Grupo_167" data-name="Grupo 167" transform="translate(11.366 9.448)">
      <g id="Grupo_166" data-name="Grupo 166">
        <path id="Caminho_122" data-name="Caminho 122" d="M385.924,222.209a3.857,3.857,0,0,0-1.576-1.394.3.3,0,1,0-.257.534,3.259,3.259,0,0,1,1.468,4.464.3.3,0,1,0,.523.278,3.853,3.853,0,0,0-.158-3.882Z" transform="translate(-383.924 -220.785)" fill="#434343"/>
      </g>
    </g>
    <g id="Grupo_169" data-name="Grupo 169" transform="translate(10.273 9.099)">
      <g id="Grupo_168" data-name="Grupo 168">
        <path id="Caminho_123" data-name="Caminho 123" d="M347.316,209h-.02a.3.3,0,1,0,0,.592h.019a.3.3,0,0,0,0-.592Z" transform="translate(-347 -209.001)" fill="#434343"/>
      </g>
    </g>
    <g id="Grupo_171" data-name="Grupo 171" transform="translate(6.276 10.985)">
      <g id="Grupo_170" data-name="Grupo 170">
        <path id="Caminho_124" data-name="Caminho 124" d="M214.442,277.611a3.259,3.259,0,0,1-1.468-4.464.3.3,0,0,0-.523-.278,3.85,3.85,0,0,0,1.734,5.276.3.3,0,1,0,.257-.534Z" transform="translate(-212.002 -272.711)" fill="#434343"/>
      </g>
    </g>
    <g id="Grupo_173" data-name="Grupo 173" transform="translate(9.366 16.204)">
      <g id="Grupo_172" data-name="Grupo 172">
        <path id="Caminho_125" data-name="Caminho 125" d="M316.692,449h-.017a.3.3,0,1,0,0,.592h.02a.3.3,0,1,0,0-.592Z" transform="translate(-316.378 -448.999)" fill="#434343"/>
      </g>
    </g>
    <g id="Grupo_175" data-name="Grupo 175" transform="translate(0 3)">
      <g id="Grupo_174" data-name="Grupo 174">
        <path id="Caminho_126" data-name="Caminho 126" d="M11.469,8.1a1.359,1.359,0,0,0-.132-.242h.771a1.362,1.362,0,1,0,0-2.724H9.738A1.361,1.361,0,0,0,8.615,3H1.362a1.362,1.362,0,0,0,0,2.724H3.733a1.358,1.358,0,0,0,0,1.539H2.96a1.361,1.361,0,0,0-.846,2.428,1.359,1.359,0,0,0,0,2.132,1.359,1.359,0,0,0,0,2.132,1.361,1.361,0,0,0,.846,2.428H6.449A5.032,5.032,0,1,0,11.469,8.1Zm.639-2.373a.77.77,0,0,1,0,1.539H4.855a.77.77,0,1,1,0-1.539ZM1.362,5.132a.77.77,0,0,1,0-1.539H8.615a.77.77,0,0,1,0,1.539Zm1.6,2.724h7.253a.769.769,0,0,1,.336.077c-.14-.012-.282-.018-.425-.018a5.017,5.017,0,0,0-3.561,1.48H2.96a.77.77,0,1,1,0-1.539Zm0,2.132h3.1A5.018,5.018,0,0,0,5.3,11.526H2.96a.77.77,0,1,1,0-1.539Zm0,2.132h2.2a5.037,5.037,0,0,0-.018,1.539H2.96a.77.77,0,1,1,0-1.539Zm0,3.671a.77.77,0,0,1,0-1.539h2.3a5.013,5.013,0,0,0,.71,1.539Zm7.164,1.6a4.441,4.441,0,1,1,4.441-4.441A4.446,4.446,0,0,1,10.125,17.388Z" transform="translate(0 -3)" fill="#434343"/>
      </g>
    </g>
  </g>
</svg>
`;
  const SvgImage = () => <SvgXml xml={svgMarkup} width={width * 0.05} height={height * 0.025} />;  

  return <SvgImage />;
}