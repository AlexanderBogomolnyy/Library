package ua.training.library.controller.validator;


import java.util.Arrays;

public class EnumValidator extends AbstractValidator<String> {

    private Class<? extends Enum> en;

    public EnumValidator(Class<? extends Enum> en, String message) {
        super(message);
        this.en = en;
    }

    @Override
    public boolean validate(String string) {
        Enum[] constants = en.getEnumConstants();
        return Arrays.asList(constants)
                .stream()
                .map(Enum::name)
                .filter(x -> x.equals(string))
                .findFirst()
                .isPresent();
    }
}
