package fr.remy.cc1.kernel;


public class EnumValidator<E extends Enum> implements Validator<String> {

   private Class<? extends Enum> enumType;


   public EnumValidator(Class<? extends Enum> enumType) {
      this.enumType = enumType;
   }


   @Override
   public boolean test(String aspirant) {
      try {
         E.valueOf(this.enumType , aspirant);
         return true;
      }catch(IllegalArgumentException illegalArgumentException) {
         return false;
      }
   }
}
