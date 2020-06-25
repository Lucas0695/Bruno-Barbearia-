/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.AgendamentoHorario;
import entidade.Cliente;
import entidade.Colaborador;
import entidade.ItensServico;
import entidade.ItensVenda;
import entidade.OrdemServico;
import facade.AgendamentoHorarioFacade;
import facade.ClienteFacade;
import facade.ColaboradorFacade;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;
import util.ScheduleEventAgenda;

/**
 *
 * @author Lucas
 */
@Named
@ViewScoped
public class AgendamentoControle implements Serializable {

    @Inject
    private AgendamentoHorarioFacade agendamentoHorarioFacade;
    @Inject
    private ClienteFacade clienteFacade;
    @Inject
    private ColaboradorFacade colaboradorFacade;
    private ConverterGenerico clienteConverter;
    private ConverterGenerico colaboradorConverter;
    private AgendamentoHorario agen;
    private Cliente cliente;
    private Colaborador colaborador;
    private ScheduleModel eventModel;
    private String titulo;
    private List<AgendamentoHorario> agendamentos;
    private OrdemServico ordemServico;
    private OrdemServicoControle ordemServicoControle;
    private ItensServico itensServico;
    private ItensVenda itensVenda;
    

    public void novoAg() {
        ordemServicoControle.novo();
        ordemServico.setCliente(agen.getCliente());
        ordemServico.setColaborador(event.getAgendamentoHorario().getColaborador());

    }

    public OrdemServicoControle getOrdemServicoControle() {
        return ordemServicoControle;
    }

    public void setOrdemServicoControle(OrdemServicoControle ordemServicoControle) {
        this.ordemServicoControle = ordemServicoControle;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }


    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public ConverterGenerico getClienteConverter() {
        if (clienteConverter == null) {
            clienteConverter = new ConverterGenerico(clienteFacade);
        }
        return clienteConverter;
    }

    public void setClienteConverter(ConverterGenerico clienteConverter) {
        this.clienteConverter = clienteConverter;
    }

    public ConverterGenerico getColaboradorConverter() {
        if (colaboradorConverter == null) {
            colaboradorConverter = new ConverterGenerico(colaboradorFacade);
        }
        return colaboradorConverter;
    }

    public void setColaboradorConverter(ConverterGenerico colaboradorConverter) {
        this.colaboradorConverter = colaboradorConverter;
    }

    public List<Cliente> listaFiltrandoCliente(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome");
    }

    public List<Colaborador> listaFiltrandoColaborador(String parte) {
        return colaboradorFacade.listaFiltrando(parte, "nome");
    }

    private ScheduleModel lazyEventModel;

    private ScheduleEventAgenda event = new ScheduleEventAgenda();

    public AgendamentoHorario getAgen() {
        return agen;
    }

    public void setAgen(AgendamentoHorario agen) {
        this.agen = agen;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void carregaEventos() {
        eventModel = new DefaultScheduleModel();
        agendamentos = agendamentoHorarioFacade.listaTodos();
        for (AgendamentoHorario a : agendamentos) {
            ScheduleEventAgenda ds = new ScheduleEventAgenda(a.getDescricao() + " - Cliente: " + a.getCliente().getNome() + "   -   Colaborador : " + a.getColaborador().getNome(), a.getDataInicial(), a.getDataFinal());
            ds.setAgendamentoHorario(a);
            eventModel.addEvent(ds);
        }
    }

    @PostConstruct
    public void init() {
        carregaEventos();
        cliente = new Cliente();
        colaborador = new Colaborador();
        agen = new AgendamentoHorario();
        //  eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));

                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }
        };
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleEventAgenda getEvent() {
        return event;
    }

    public void setEvent(ScheduleEventAgenda event) {
        this.event = event;
    }

    public void addEvent() {
        criarEvento();
        carregaEventos();
    }

    public void excluir() {
        agendamentoHorarioFacade.excluir(event.getAgendamentoHorario());
        carregaEventos();
    }

    public void criarEvento() {
        agen.setDescricao(event.getTitle());
        agen.setDataFinal(event.getEndDate());
        agen.setDataInicial(event.getStartDate());
        agendamentoHorarioFacade.salvar(agen);
        agen = new AgendamentoHorario();
        event = new ScheduleEventAgenda();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEventAgenda) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new ScheduleEventAgenda("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
