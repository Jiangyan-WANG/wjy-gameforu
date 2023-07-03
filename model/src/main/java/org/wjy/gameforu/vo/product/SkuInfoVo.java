package org.wjy.gameforu.vo.product;

import org.wjy.gameforu.model.product.*;
import org.wjy.gameforu.model.product.SkuAttrValue;
import org.wjy.gameforu.model.product.SkuImage;
import org.wjy.gameforu.model.product.SkuInfo;
import org.wjy.gameforu.model.product.SkuPoster;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SkuInfoVo extends SkuInfo {

	@ApiModelProperty(value = "海报列表")
	private List<SkuPoster> skuPosterList;

	@ApiModelProperty(value = "属性值")
	private List<SkuAttrValue> skuAttrValueList;

	@ApiModelProperty(value = "图片")
	private List<SkuImage> skuImagesList;

}

