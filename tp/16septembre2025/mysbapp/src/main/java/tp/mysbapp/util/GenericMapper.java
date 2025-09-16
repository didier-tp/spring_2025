package tp.mysbapp.util;

import java.util.List;
import org.springframework.beans.BeanUtils;
/*
 * GenericMapper = mapper/convertisseur hyper générique utilisant 
 * seulement BeanUtils.copyProperties
 */
public class GenericMapper {
	
	public static GenericMapper MAPPER = new GenericMapper();

	//GenericMapper.MAPPER.map(productEntity,Product.class) 
	public  <S,D> D map(S source , Class<D> targetClass) {
		D target  = null;
		try {
			target = targetClass.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			throw new RuntimeException("echec GenericMapper.map",e);
		} 
		return target;
	}
	
	//GenericMapper.MAPPER.map(ListeProductEntity,Product.class) 
	public  <S,D> List<D> map(List<S> sourceList , Class<D> targetClass){
		return  sourceList.stream()
			   .map((source)->map(source,targetClass))
			   .toList();
	}

}