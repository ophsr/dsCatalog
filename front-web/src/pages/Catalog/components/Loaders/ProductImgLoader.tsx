import React from "react"
import ContentLoader from "react-content-loader"

const ProductImgLoader = () => (
  <ContentLoader 
    speed={1}
    width={160}
    height={160}
    viewBox="0 0 160 160"
    backgroundColor="#ecebeb"
    foregroundColor="#d6d2d2"
  >
    <rect x="0" y="0" rx="10" ry="10" width="160" height="160" />

  </ContentLoader>
)

export default ProductImgLoader

