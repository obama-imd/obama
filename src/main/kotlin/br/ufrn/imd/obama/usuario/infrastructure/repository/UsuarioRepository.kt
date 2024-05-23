package br.ufrn.imd.obama.usuario.infrastructure.repository

import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsuarioRepository: JpaRepository<UsuarioEntity, Long> {
    fun findByEmail(login: String): UserDetails?
}
