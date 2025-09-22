GenericMapper with BeanUtils.copyProperties and intropection at runtime is a very basic mapper/converter implementation:
  - bad performance
  - works only with fields with same names

=====
MyMapStructMapper is more sophisticated (good perf and possibility of fields with différents names)
NB: pom.xml must be configured for using Lombok & mapstruct together .