import React from "react";
import { Dimensions } from 'react-native'
import { SvgXml } from "react-native-svg";  

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

export default function BackIconSvg(){  
  const svgMarkup = `<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="48" height="48" viewBox="0 0 48 48">
  <defs>
    <filter id="Elipse_1" x="0" y="0" width="48" height="48" filterUnits="userSpaceOnUse">
      <feOffset dx="2" dy="3" input="SourceAlpha"/>
      <feGaussianBlur stdDeviation="3" result="blur"/>
      <feFlood flood-opacity="0.161"/>
      <feComposite operator="in" in2="blur"/>
      <feComposite in="SourceGraphic"/>
    </filter>
  </defs>
  <g id="Grupo_178" data-name="Grupo 178" transform="translate(-9 -26)">
    <g transform="matrix(1, 0, 0, 1, 9, 26)" filter="url(#Elipse_1)">
      <circle id="Elipse_1-2" data-name="Elipse 1" cx="15" cy="15" r="15" transform="translate(7 6)" fill="#fff"/>
    </g>
    <g id="Grupo_88" data-name="Grupo 88" transform="translate(4 6)">
      <line id="Linha_1" data-name="Linha 1" x1="6.667" y2="6.667" transform="translate(22.5 34.5)" fill="none" stroke="#ffb726" stroke-linecap="round" stroke-width="2"/>
      <path id="Caminho_4" data-name="Caminho 4" d="M6.667,0,4.584,2.084,0,6.667" transform="translate(29.167 41.167) rotate(90)" fill="none" stroke="#ffb726" stroke-linecap="round" stroke-width="2"/>
    </g>
  </g>
</svg>
`;
  const SvgImage = () => <SvgXml xml={svgMarkup} width={width * 0.2} />;  

  return <SvgImage />;
}