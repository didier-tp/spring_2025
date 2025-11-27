package tp.appliSpring.bank.converter;

import org.springframework.stereotype.Component;
import tp.appliSpring.generic.converter.AbstractGenericConverter;

/*
MyBankGenericMapper inherit AbstractGenericConverter which implements GenericConverter
and is implemented by UltraBasicGenericMapper if no mapstruct Converter is set
if a mapstruct Converter is set, it will be used by AbstractGenericConverter
if methods convention name xxxToYyy()
 */
@Component
public class MyBankGenericMapper extends AbstractGenericConverter {
    public static MyBankGenericMapper CONVERTER = new MyBankGenericMapper();

    @Override
    public Object getDtoConverter() {
        return MyBankConverter.INSTANCE;
    }
}