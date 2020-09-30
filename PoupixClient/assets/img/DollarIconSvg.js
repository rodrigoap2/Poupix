import React from "react";
import { Dimensions } from 'react-native'
import { SvgXml } from "react-native-svg";  

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

export default function DollarIconSvg(){  
  const svgMarkup = `<svg id="dollar" xmlns="http://www.w3.org/2000/svg" width="17.111" height="17.111" viewBox="0 0 17.111 17.111">
  <path id="Caminho_45" data-name="Caminho 45" d="M206,138.674a1.673,1.673,0,0,0,1.671,1.671,1,1,0,1,1-1,1,.334.334,0,1,0-.668,0,1.674,1.674,0,0,0,1.337,1.637v.7a.334.334,0,1,0,.668,0v-.7a1.671,1.671,0,0,0-.334-3.308,1,1,0,1,1,1-1,.334.334,0,0,0,.668,0,1.674,1.674,0,0,0-1.337-1.637v-.7a.334.334,0,1,0-.668,0v.7A1.674,1.674,0,0,0,206,138.674Z" transform="translate(-199.115 -131.455)"/>
  <path id="Caminho_46" data-name="Caminho 46" d="M8.556,0A8.71,8.71,0,0,0,0,8.556a8.71,8.71,0,0,0,8.556,8.556,8.71,8.71,0,0,0,8.556-8.556A8.71,8.71,0,0,0,8.556,0Zm0,16.443A8.094,8.094,0,0,1,.668,8.556,8.094,8.094,0,0,1,8.556.668a8.094,8.094,0,0,1,7.887,7.887A8.094,8.094,0,0,1,8.556,16.443Z"/>
  <path id="Caminho_47" data-name="Caminho 47" d="M67.959,64.7a.334.334,0,1,0-.154.65,5.882,5.882,0,1,1-2.508,0,.334.334,0,1,0-.154-.65,6.55,6.55,0,1,0,2.817,0Z" transform="translate(-57.995 -62.534)"/>
  <circle id="Elipse_2" data-name="Elipse 2" cx="0.5" cy="0.5" r="0.5" transform="translate(8.219 1.892)" fill="#d5d5d5"/>
</svg>
`;

  const SvgImage = () => <SvgXml xml={svgMarkup} width={width * 0.05} />;  

  return <SvgImage />;
}