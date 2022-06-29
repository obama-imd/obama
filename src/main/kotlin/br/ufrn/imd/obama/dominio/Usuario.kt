package br.ufrn.imd.obama.dominio

import br.ufrn.imd.obama.enums.Perfil
import br.ufrn.imd.obama.enums.StatusUsuario
import br.ufrn.imd.obama.enums.TipoCadastro
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name="usuario", schema="public")
data class Usuario (
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Int = 0,

	@Column(name = "nome")
	val nome: String = "",

	@Column(name = "email", unique = true, nullable = false)
	val email: String = "",

	@Column(name = "senha")
	val senha: String = "",

	@Enumerated(EnumType.STRING)
	@Column(name="perfil")
	val perfil: Perfil = Perfil.PADRAO,

	@Enumerated(EnumType.STRING)
	@Column(name="statusUsuario")
	val statusUsuario: StatusUsuario = StatusUsuario.INATIVO,

	@Enumerated(EnumType.STRING)
	@Column(name="tipoCadastro")
	val tipoCadastro: TipoCadastro = TipoCadastro.PADRAO,

	@Column(name = "token")
	val token: String = "",

	@Column(name="dataCadastro")
	var dataCadastro: LocalDate = LocalDate.now(),

	@Column(name="ultimoAcesso")
	var ultimoAcesso: LocalDate = LocalDate.now(),
)
