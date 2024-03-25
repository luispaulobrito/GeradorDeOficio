import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/app/environment/environment';
import { StorageService } from '../services/storage.service';

export class BaseAPI {
  protected apiUrl: string = environment.apiUrl;

  constructor(
    protected httpClient: HttpClient,
    protected storageService: StorageService
  ) {}

  /**
   * Configura e retorna um conjunto de cabeçalhos HTTP padrão para serem utilizados em requisições HTTP.
   * @returns Um objeto contendo os cabeçalhos HTTP configurados.
   */
  public setHeaders(): HttpHeaders['headers'] {
    const bearerToken = this.storageService.getItem('token');

    const headers: HttpHeaders['headers'] = {
      language: 'pt-br',
      ...(bearerToken && { authorization: `Bearer ${bearerToken}` }),
      'Content-Type': 'application/json',
    };

    return headers;
  }

  /**
   * Realiza uma requisição HTTP POST para a URL da API.
   *
   * @param data Os dados a serem enviados na requisição.
   * @returns Um Observable com a resposta da API se a requisição for bem-sucedida, ou uma mensagem de erro em caso de falha.
   */
  public post<TD, TR>(data: TD): Observable<TR> {
    const headers = this.setHeaders();
    return this.httpClient.post<TR>(this.apiUrl, data, { headers });
  }

  /**
   * Realiza uma requisição HTTP GET para a URL da API.
   * @param params String contendo alguns parametros caso necessário para rotas GET
   * @returns Um Observable com a resposta da API no resolve se a requisição for bem-sucedida, ou uma mensagem de erro em caso de falha.
   */
  public get<TR>(params = ''): Observable<TR> {
    const headers = this.setHeaders();
    return this.httpClient.get<TR>(this.apiUrl + params, { headers });
  }
}
