# e-commerce-doc-api
- use for upload img
- method: POST
  - endpoint: localhost:9998/upload/img
    - body (form data): 
      - file:
      - name: name of img
      - dir: directory
- To read image
    - method: GET
    - endpoint: localhost:9998/img/directory/name of image