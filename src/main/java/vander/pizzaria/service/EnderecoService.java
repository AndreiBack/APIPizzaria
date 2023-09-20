package vander.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import vander.pizzaria.dto.EnderecoDTO;
import vander.pizzaria.entity.Endereco;
import vander.pizzaria.repository.EnderecoRepository;

import java.util.List;


@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ModelMapper modelMapper;

    private Endereco toEndereco(EnderecoDTO enderecoDTO){
        return modelMapper.map(enderecoDTO, Endereco.class);
    }
    private EnderecoDTO toEnderecoDTO(Endereco endereco){
        return modelMapper.map(endereco, EnderecoDTO.class);
    }
    private void idNotNull(Long id){
        Assert.notNull(enderecoRepository.findById(id).orElse(null), String.format("ID [%s] não encontrado" , id));
    }
    private void validationEnderecoDTO(EnderecoDTO enderecoDTO){
        Assert.notNull(enderecoDTO.getBairro(), "Informe o Bairro!");
        Assert.hasText(enderecoDTO.getBairro(), "Informe o Bairro!");
        Assert.notNull(enderecoDTO.getNumero(), "Informe o Numero!");
        Assert.notNull(enderecoDTO.getCep(), "Informe o Cep!");
        Assert.notNull(enderecoDTO.getRua(), "Informe o Rua!");
        Assert.hasText(enderecoDTO.getRua(), "Informe o Rua!");
    }
    public EnderecoDTO findById(Long id){
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        return toEnderecoDTO(endereco);
    }
    public List<EnderecoDTO> findAll(){
        return enderecoRepository.findAll().stream().map(this::toEnderecoDTO).toList();
    }
    @Transactional(rollbackFor = Exception.class)
    public String create(EnderecoDTO enderecoDTO){
        validationEnderecoDTO(enderecoDTO);
        toEnderecoDTO(enderecoRepository.save(toEndereco(enderecoDTO)));
        return "Sucesso ao cadastrar novo Registro";
    }
    @Transactional(rollbackFor = Exception.class)
    public String update(Long id, EnderecoDTO enderecoDTO){
        idNotNull(id);
        validationEnderecoDTO(enderecoDTO);
        toEnderecoDTO(enderecoRepository.save(toEndereco(enderecoDTO)));
        return "Sucesso ao atualizar Registro do ID:" + id + " Cliente";
    }
    public void delete(Long id){
        idNotNull(id);
        enderecoRepository.deleteById(id);
    }



}
