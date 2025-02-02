
$(() => {
    // event listener for checkboxes for directions
    $("#noutlanes").on("click", ".directionchbox", () => {
        if (!$(this).is(":checked")) {
            const name = $(this).attr("name");
            console.log(name);
            // const num = name.substr(1, name.length - 3);
            // console.log(num);
        }
    });

    // when the number of lanes changes, we must truncate or generate the other lanes
    // dynamically
    for (const direction of ["n"]) {
        const dropdownOut = `#${direction}numlanesout`;
        $(dropdownOut).on("change", () => {
            const numLanes = Number.parseInt($(`${dropdownOut} option:selected`).val());
    
            // this shit below just didn't work with jquery idk why
            const table = document.getElementById("noutlanes");
            
            table.innerHTML = ""; // Clear existing table
            
            const headerRow = table.insertRow();
            for (let i = 0; i < numLanes; i++) {
                const th = document.createElement("th");
                th.textContent = `Lane ${i + 1}`;
                headerRow.appendChild(th);
            }
            
            const dataRow = table.insertRow();
            for (let i = 0; i < numLanes; i++) {
                const td = document.createElement("td");
                td.innerHTML = `
                <strong>Available outbound directions:</strong>
                <br>
                <label for="n${i+1}toe">&larr;</label>
                <input class="directionchbox" type="checkbox" id="n${i+1}toe" name="n${i+1}toe" checked>
                <label for="n${i+1}toe">&darr;</label>
                <input class="directionchbox" type="checkbox" id="n${i+1}tos" name="n${i+1}tos" checked>
                <label for="n${i+1}toe">&rarr;</label>
                <input class="directionchbox" type="checkbox" id="n${i+1}tow" name="n${i+1}tow" checked>
                <br>
                `
                dataRow.appendChild(td);
            }
        });
    }

    // force dropdown onchange event to generate list of lanes
    $("#nnumlanesin").trigger("change");
    $("#nnumlanesout").trigger("change");

    $("#npedcrossing").on("change", () => {
        const checked = $("#npedcrossing").is(":checked");
        if (!checked) {
            $("#neditcrossing").attr("disabled", true);
        } else {
            $("#neditcrossing").removeAttr("disabled");
        }
    });

    $("#npedcrossing").trigger("change");

    // modal pop-up window setups
    const modalSetups = [
        ["#edittrafficlight", "#closetrafficlightmodal", "#trafficlightmodal"],
        ["#neditcrossing", "#nclosecrossingsettingsmodal", "#ncrossingsettingsmodal"]
    ];

    for (const [openBtn, closeBtn, modal] of modalSetups) {
        $(openBtn).on('click', (e) => {
            e.preventDefault();
            $(modal).css({display: "block"});
        });
    
        $(closeBtn).on('click', (e) => {
            e.preventDefault();
            $(modal).css({display: "none"});
        }); 
    }
});