import React from "react";
import { Dimensions } from 'react-native'
import { SvgXml } from "react-native-svg";  

const width = Dimensions.get('window').width; 
const height = Dimensions.get('window').height; 

export default function ShopIconSvg(){  
  const svgMarkup = `<svg xmlns="http://www.w3.org/2000/svg" width="17.113" height="13.898" viewBox="0 0 17.113 13.898">
  <g id="shop" transform="translate(0 -48.096)">
    <g id="Grupo_118" data-name="Grupo 118" transform="translate(0 48.096)">
      <g id="Grupo_117" data-name="Grupo 117" transform="translate(0 0)">
        <path id="Caminho_61" data-name="Caminho 61" d="M16.326,61.492h-.285V54.056A2.327,2.327,0,0,0,17.113,52.1a.251.251,0,0,0-.033-.124l-1.989-3.481a.788.788,0,0,0-.683-.4H2.705a.788.788,0,0,0-.683.4L.033,51.973A.251.251,0,0,0,0,52.1a2.327,2.327,0,0,0,1.072,1.958v7.437H.787a.251.251,0,0,0,0,.5h15.54a.251.251,0,1,0,0-.5ZM.519,52.348h.8a.251.251,0,1,0,0-.5H.683l1.775-3.106a.286.286,0,0,1,.248-.144h11.7a.286.286,0,0,1,.248.144l1.775,3.106h-.64a.251.251,0,1,0,0,.5h.8a1.826,1.826,0,0,1-3.617,0h1.741a.251.251,0,0,0,0-.5H2.394a.251.251,0,1,0,0,.5H4.135a1.826,1.826,0,0,1-3.617,0Zm11.922,0a1.825,1.825,0,0,1-3.616,0Zm-4.153,0a1.825,1.825,0,0,1-3.616,0ZM14.2,61.492H11.486v-.838H14.2Zm0-1.34H11.486V55.563H14.2Zm1.34,1.34H14.7v-6.18a.251.251,0,0,0-.251-.251H11.236a.251.251,0,0,0-.251.251v6.18H1.573V54.3A2.325,2.325,0,0,0,4.4,53.148a2.327,2.327,0,0,0,4.153,0,2.327,2.327,0,0,0,4.153,0A2.325,2.325,0,0,0,15.54,54.3Z" transform="translate(0 -48.096)" fill="#434343"/>
      </g>
    </g>
    <g id="Grupo_120" data-name="Grupo 120" transform="translate(2.411 55.062)">
      <g id="Grupo_119" data-name="Grupo 119">
        <path id="Caminho_62" data-name="Caminho 62" d="M79.63,256.516H72.4a.251.251,0,0,0-.251.251v5.091a.251.251,0,0,0,.251.251H79.63a.251.251,0,0,0,.251-.251v-5.091A.251.251,0,0,0,79.63,256.516Zm-.251,5.091H72.646v-4.589h6.733Z" transform="translate(-72.145 -256.516)" fill="#434343"/>
      </g>
    </g>
  </g>
</svg>`;

  const SvgImage = () => <SvgXml xml={svgMarkup} width={width * 0.05} />;  

  return <SvgImage />;
}