
# Docker image oluşturmak için

    docker build -t javaboost2/java15x:v01 .  -> applechip çıktı aynı olur

    DİKKAT!!! 
    apple chip ler için yukarıdaki image cloud sistemlerinde sorun çıkartır. Bu nedenle 
    platform bilgisinin bu işleme dahil edilmesi gerelidir.

    docker build --platform linux/amd64 -t javaboost2/java15x:v01 .

    reactJS image oluşturma
    docker build --platform linux/amd64 -t javaboost2/java15x-react:v01 .
