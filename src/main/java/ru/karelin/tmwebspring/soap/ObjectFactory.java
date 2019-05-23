
package ru.karelin.tmwebspring.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.karelin.tmwebspring.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetTaskById_QNAME = new QName("http://soap.tmwebspring.karelin.ru/", "getTaskById");
    private final static QName _GetTaskByIdResponse_QNAME = new QName("http://soap.tmwebspring.karelin.ru/", "getTaskByIdResponse");
    private final static QName _GetTaskList_QNAME = new QName("http://soap.tmwebspring.karelin.ru/", "getTaskList");
    private final static QName _GetTaskListByProjectId_QNAME = new QName("http://soap.tmwebspring.karelin.ru/", "getTaskListByProjectId");
    private final static QName _GetTaskListByProjectIdResponse_QNAME = new QName("http://soap.tmwebspring.karelin.ru/", "getTaskListByProjectIdResponse");
    private final static QName _GetTaskListResponse_QNAME = new QName("http://soap.tmwebspring.karelin.ru/", "getTaskListResponse");
    private final static QName _RemoveTaskById_QNAME = new QName("http://soap.tmwebspring.karelin.ru/", "removeTaskById");
    private final static QName _RemoveTaskByIdResponse_QNAME = new QName("http://soap.tmwebspring.karelin.ru/", "removeTaskByIdResponse");
    private final static QName _UpdateTask_QNAME = new QName("http://soap.tmwebspring.karelin.ru/", "updateTask");
    private final static QName _UpdateTaskResponse_QNAME = new QName("http://soap.tmwebspring.karelin.ru/", "updateTaskResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.karelin.tmwebspring.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTaskById }
     * 
     */
    public GetTaskById createGetTaskById() {
        return new GetTaskById();
    }

    /**
     * Create an instance of {@link GetTaskByIdResponse }
     * 
     */
    public GetTaskByIdResponse createGetTaskByIdResponse() {
        return new GetTaskByIdResponse();
    }

    /**
     * Create an instance of {@link GetTaskList }
     * 
     */
    public GetTaskList createGetTaskList() {
        return new GetTaskList();
    }

    /**
     * Create an instance of {@link GetTaskListByProjectId }
     * 
     */
    public GetTaskListByProjectId createGetTaskListByProjectId() {
        return new GetTaskListByProjectId();
    }

    /**
     * Create an instance of {@link GetTaskListByProjectIdResponse }
     * 
     */
    public GetTaskListByProjectIdResponse createGetTaskListByProjectIdResponse() {
        return new GetTaskListByProjectIdResponse();
    }

    /**
     * Create an instance of {@link GetTaskListResponse }
     * 
     */
    public GetTaskListResponse createGetTaskListResponse() {
        return new GetTaskListResponse();
    }

    /**
     * Create an instance of {@link RemoveTaskById }
     * 
     */
    public RemoveTaskById createRemoveTaskById() {
        return new RemoveTaskById();
    }

    /**
     * Create an instance of {@link RemoveTaskByIdResponse }
     * 
     */
    public RemoveTaskByIdResponse createRemoveTaskByIdResponse() {
        return new RemoveTaskByIdResponse();
    }

    /**
     * Create an instance of {@link UpdateTask }
     * 
     */
    public UpdateTask createUpdateTask() {
        return new UpdateTask();
    }

    /**
     * Create an instance of {@link UpdateTaskResponse }
     * 
     */
    public UpdateTaskResponse createUpdateTaskResponse() {
        return new UpdateTaskResponse();
    }

    /**
     * Create an instance of {@link TaskDto }
     * 
     */
    public TaskDto createTaskDto() {
        return new TaskDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.tmwebspring.karelin.ru/", name = "getTaskById")
    public JAXBElement<GetTaskById> createGetTaskById(GetTaskById value) {
        return new JAXBElement<GetTaskById>(_GetTaskById_QNAME, GetTaskById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.tmwebspring.karelin.ru/", name = "getTaskByIdResponse")
    public JAXBElement<GetTaskByIdResponse> createGetTaskByIdResponse(GetTaskByIdResponse value) {
        return new JAXBElement<GetTaskByIdResponse>(_GetTaskByIdResponse_QNAME, GetTaskByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.tmwebspring.karelin.ru/", name = "getTaskList")
    public JAXBElement<GetTaskList> createGetTaskList(GetTaskList value) {
        return new JAXBElement<GetTaskList>(_GetTaskList_QNAME, GetTaskList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskListByProjectId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.tmwebspring.karelin.ru/", name = "getTaskListByProjectId")
    public JAXBElement<GetTaskListByProjectId> createGetTaskListByProjectId(GetTaskListByProjectId value) {
        return new JAXBElement<GetTaskListByProjectId>(_GetTaskListByProjectId_QNAME, GetTaskListByProjectId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskListByProjectIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.tmwebspring.karelin.ru/", name = "getTaskListByProjectIdResponse")
    public JAXBElement<GetTaskListByProjectIdResponse> createGetTaskListByProjectIdResponse(GetTaskListByProjectIdResponse value) {
        return new JAXBElement<GetTaskListByProjectIdResponse>(_GetTaskListByProjectIdResponse_QNAME, GetTaskListByProjectIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.tmwebspring.karelin.ru/", name = "getTaskListResponse")
    public JAXBElement<GetTaskListResponse> createGetTaskListResponse(GetTaskListResponse value) {
        return new JAXBElement<GetTaskListResponse>(_GetTaskListResponse_QNAME, GetTaskListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveTaskById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.tmwebspring.karelin.ru/", name = "removeTaskById")
    public JAXBElement<RemoveTaskById> createRemoveTaskById(RemoveTaskById value) {
        return new JAXBElement<RemoveTaskById>(_RemoveTaskById_QNAME, RemoveTaskById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveTaskByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.tmwebspring.karelin.ru/", name = "removeTaskByIdResponse")
    public JAXBElement<RemoveTaskByIdResponse> createRemoveTaskByIdResponse(RemoveTaskByIdResponse value) {
        return new JAXBElement<RemoveTaskByIdResponse>(_RemoveTaskByIdResponse_QNAME, RemoveTaskByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.tmwebspring.karelin.ru/", name = "updateTask")
    public JAXBElement<UpdateTask> createUpdateTask(UpdateTask value) {
        return new JAXBElement<UpdateTask>(_UpdateTask_QNAME, UpdateTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.tmwebspring.karelin.ru/", name = "updateTaskResponse")
    public JAXBElement<UpdateTaskResponse> createUpdateTaskResponse(UpdateTaskResponse value) {
        return new JAXBElement<UpdateTaskResponse>(_UpdateTaskResponse_QNAME, UpdateTaskResponse.class, null, value);
    }

}
