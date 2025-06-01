package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagemPlataforma
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import java.time.LocalDate

interface ObjetoAprendizagemUseCase {

    fun buscarPorId(
        id: Long
    ): ObjetoAprendizagem

    fun buscarPorParametros(
        pageable: Pageable,
        nome: String?,
        tipoAcesso: TipoAcesso?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        anoEnsinoId: Long?,
        habilidadeId: Long?
    ): Page<ObjetoAprendizagem>

    /**
     * Cadastra um novo Objeto de Aprendizagem.
     *
     * Este método realiza as seguintes etapas:
     * 1. Busca todas as entidades relacionadas (tipo de licença de uso, idiomas, autores/mantenedores, descritores e habilidades) a partir dos seus respectivos IDs.
     *    Caso alguma entidade não seja encontrada, lança uma exceção específica.
     * 2. Valida se a quantidade de acessos é não-negativa.
     * 3. Monta o objeto de aprendizagem utilizando o builder e os dados informados.
     * 4. Persiste o objeto de aprendizagem utilizando o gateway.
     *
     * @param nome Nome do objeto de aprendizagem.
     * @param descricao Descrição do objeto de aprendizagem.
     * @param quantidadeAcessos Quantidade de acessos iniciais.
     * @param thumbnailPath Caminho da imagem de thumbnail (opcional).
     * @param dataLancamento Data de lançamento (opcional).
     * @param versao Versão do objeto (opcional).
     * @param ativo Indica se o objeto está ativo.
     * @param tipoLicensaUsoId ID do tipo de licença de uso.
     * @param idiomaIds Conjunto de IDs dos idiomas.
     * @param autorMantenedorIds Conjunto de IDs dos autores/mantenedores.
     * @param descritorIds Conjunto de IDs dos descritores.
     * @param habilidadeIds Conjunto de IDs das habilidades.
     * @param plataformas Lista de plataformas associadas.
     *
     * @return O objeto de aprendizagem cadastrado.
     *
     * @throws TipoLicensaUsoNaoEncontradoException Se o tipo de licença de uso não for encontrado.
     * @throws IdiomaNaoEncontradoException Se algum idioma não for encontrado.
     * @throws AutorMantenedorNaoEncontradoException Se algum autor/mantenedor não for encontrado.
     * @throws DescritorNaoEncontradoException Se algum descritor não for encontrado.
     * @throws HabilidadeNaoEncontradaException Se alguma habilidade não for encontrada.
     * @throws IllegalArgumentException Se a quantidade de acessos for negativa.
     */

    fun cadastrarObjetoAprendizagem(
        nome: String,
        descricao: String,
        quantidadeAcessos: Int,
        thumbnailPath: String?,
        dataLancamento: LocalDate?,
        versao: String?,
        ativo: Boolean,
        tipoLicensaUsoId: Long,
        idiomaIds: Set<Long>,
        autorMantenedorIds: Set<Long>,
        descritorIds: Set<Long>,
        habilidadeIds: Set<Long>,
        plataformas: List<Long>
    ): ObjetoAprendizagem
}
