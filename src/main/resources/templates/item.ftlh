<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="/item/style.css">
    <link rel="stylesheet" href="/buttons.css">
    <title>CSGODatabase Viewer</title>
</head>

<body>
    <div class="container">
        <div class="center grid">
            <h1 class="heading">${collection}</h1>

            <ul class="list">
                <#list skins as skin>
                    <li>
                        <img src="${skin.image}" alt="picture" width="250">
                        <div class="left">
                            <h3>${skin.name}</h3>
                            <p>Rarity: <a style="color: ${skin.color}">${skin.rarity}</a></p>
                            <p>Chance: ${skin.dropRate}</p>
                        </div>
                    </li>
                </#list>
            </ul>

            <div class="buttons">
                <a href="/" class="btn-hover with-radius color-2">Back</a>
            </div>
        </div>
    </div>
</body>

</html>