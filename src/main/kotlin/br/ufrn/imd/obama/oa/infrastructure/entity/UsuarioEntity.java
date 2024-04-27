package br.ufrn.imd.obama.oa.infrastructure.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Table(name = "usuario")
class UsuarioEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_gen")
        @SequenceGenerator(name="usuario_gen", sequenceName = "sq_usuario_id", allocationSize = 1)
        val id: Long,

        @column(name = "nome")
        val nome:String,

        @column(name = "sobrenome")
        val sobrenome:String,

        @column(name = "email")
        val email:String,

        @column(name = "senha")
        val senha:String,

        @column(name = "role")
        val role:Role,

        @column(name = "ativo")
        val ativo:Boolean,

        @column(name = "tipo_cadastro")
        val tipoCadastro:TipoCadastro,

        @column(name = "token")
        val token:String

)