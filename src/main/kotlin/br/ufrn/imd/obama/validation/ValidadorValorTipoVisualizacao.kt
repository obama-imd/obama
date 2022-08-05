package br.ufrn.imd.obama.validation

import br.ufrn.imd.obama.enums.TipoVisualizacao
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ValidadorValorTipoVisualizacao: ConstraintValidator<ValorTipoVisualizacaoEnum, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value == null){
            return true
        }

        return try {
            TipoVisualizacao.valueOf(value)
            true
        } catch (ex: Exception) {
            false
        }
    }
}
